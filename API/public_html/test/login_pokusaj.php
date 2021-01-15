<?php

include_once "connection.php";
class korisnik{}



$korisnicko_ime=$_POST['korsnicko_ime'];
$lozinka=$_POST['lozinka']; 

$query=mysqli_query($con, "SELECT k.id_korisnik,k.id_uloga,k.id_clanstvo,l.id_lokacija FROM korisnik k left join lokacija l on l.id_korisnik=k.id_korisnik WHERE k.korsnicko_ime='$korisnicko_ime' AND k.lozinka='$lozinka'");

if($query->num_rows!== 0){
    $result = array();
    $result['korisnik'] = array();
    $result['lokacije'] = array();
    $i=0;
    while($row = mysqli_fetch_array($query)){
        $index['id_korisnik'] = $row['0'];
        $index['id_uloga'] = $row['1'];
        $index['id_clanstvo'] = $row['2'];
        $polje['id_lokacija'] = $row['3'];
        if($i==0){
        array_push($result['korisnik'],$index);
        $i++;
        }
        array_push($result['lokacije'],$polje);
    }
    
    
    
    
    $response=new korisnik();
    $response->success=1;
    $response->body = $result['korisnik'];
    $response->bodyLocation = $result['lokacije'];
    $response->message=" Successfully loged in";
    die(json_encode($response));
}
else{
    $response=new korisnik();
    $response->success=0;
	$response->message="Wrong username or password";
	die(json_encode($response));
}

//}



mysqli_close($con);

?>