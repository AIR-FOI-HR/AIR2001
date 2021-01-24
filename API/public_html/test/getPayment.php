<?php



include_once('connection.php');
class payment{};
$result = array();
$result['payment'] = array();
$id_korisnik=$_POST['id_korisnik'];
//$id_korisnik = 50;
$select = "SELECT zadnja_uplata FROM korisnik WHERE id_korisnik = $id_korisnik";
$query = mysqli_query($con,$select);

if($query){
    while($row = mysqli_fetch_array($query)){
    
        $index['zadnja_uplata'] = $row['0'];
    
        array_push($result['payment'],$index);
    }
    
    $response = new payment();
    $response -> success = 1;
    $response -> body = $result['payment'];
    die(json_encode($response));
}
else{
    $response=new payment();
    $response->success=0;
	die(json_encode($response));
}



?>