param(
    [Parameter(Mandatory = $true)] [string] $ResourceGroup,
    [Parameter(Mandatory = $true)] [string] $AppName,
    [Parameter(Mandatory = $true)] [string] $DbHost,
    [Parameter(Mandatory = $true)] [string] $DbUser,
    [Parameter(Mandatory = $true)] [securestring] $DbPassword,
    [string] $Location = "eastus",
    [string] $DbName = "nurse"
)

$ErrorActionPreference = "Stop"
$plainDbPassword = [System.Net.NetworkCredential]::new("", $DbPassword).Password
$registryName = (($AppName -replace '[^a-zA-Z0-9]', '').ToLower() + "acr")
$environmentName = "$AppName-env"
$imageName = "nurse:$(Get-Date -Format 'yyyyMMddHHmmss')"

az extension add --name containerapp --upgrade
az group create --name $ResourceGroup --location $Location | Out-Null
az acr create --resource-group $ResourceGroup --name $registryName --sku Basic --admin-enabled true | Out-Null
az acr build --registry $registryName --image $imageName .

$registryServer = az acr show --name $registryName --query loginServer -o tsv
$registryUser = az acr credential show --name $registryName --query username -o tsv
$registryPassword = az acr credential show --name $registryName --query 'passwords[0].value' -o tsv

az containerapp env create --name $environmentName --resource-group $ResourceGroup --location $Location | Out-Null
az containerapp create `
    --name $AppName `
    --resource-group $ResourceGroup `
    --environment $environmentName `
    --image "$registryServer/$imageName" `
    --registry-server $registryServer `
    --registry-username $registryUser `
    --registry-password $registryPassword `
    --ingress external --target-port 8080 `
    --min-replicas 1 --max-replicas 2 --cpu 1.0 --memory 2Gi `
    --secrets "db-password=$plainDbPassword" `
    --env-vars "DB_HOST=$DbHost" "DB_PORT=3306" "DB_NAME=$DbName" "DB_USER=$DbUser" "DB_SSL_MODE=REQUIRED" "DB_PASSWORD=secretref:db-password" | Out-Null

$url = az containerapp show --name $AppName --resource-group $ResourceGroup --query properties.configuration.ingress.fqdn -o tsv
Write-Host "Despliegue terminado: https://$url"
