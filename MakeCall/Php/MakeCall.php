<?php
include_once "vendor/autoload.php";
$configs = include('config.php');
$response=\Httpful\Request::post($configs["url"])->basicAuth($configs["userName"],$configs["password"])->body("number=". $configs['number'])->mime("application/x-www-form-urlencoded")->send();
echo "Response code : $response->code \n" ,"$response \n";
?>
