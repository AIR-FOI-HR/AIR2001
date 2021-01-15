<?php

include_once "connection.php";
$kod=$_GET["kod"];
$upit = mysqli_query($con,"SELECT COUNT(*) FROM korisnik k where k.status_korisnika='aktivan' AND k.kod_za_registraciju='$kod'");

if($upit->num_rows ==1){
    $azuriraj = mysqli_query($con,"UPDATE korisnik k SET k.status_korisnika='aktivan' where k.kod_za_registraciju='$kod'");
    if($azuriraj){
        echo 'Uspješna aktivacija';
    }
}

?>