<?php
session_start();
require_once ("lib/autoload.php");
if(file_exists(__DIR__ ."/../.env")){
    $dotenv = new Dotenv\Dotenv(__DIR__ . "/../");
    $dotenv->load();
    
}

$gateway = new Braintree\Gateway([
    'environment' => 'sandbox',
    'merchantId' => '2jr4skkxxtxkrk8f',
    'publicKey' => 'vmz57npjqpzkxvsm',
    'privateKey' => '829037560e61b8dca2e116dc408721dd'
]);
/*
Braintree_Configuration::environment('sandbox');
Braintree_Configuration::merchantId('2jr4skkxxtxkrk8f');
Braintree_Configuration::publicKey('vmz57npjqpzkxvsm');
Braintree_Configuration::privateKey('829037560e61b8dca2e116dc408721dd');
*/
?>