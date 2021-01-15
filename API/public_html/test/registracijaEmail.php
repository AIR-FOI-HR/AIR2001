<?php

include_once "connection.php";
class korisnik{}


    $imeKorisnika=$_POST['ime_korisnika'];
    $prezime=$_POST['prezime_korisnika'];
    $emailKorisnika=$_POST['email_korisnika'];
    $korisnickoIme=$_POST['korsnicko_ime'];
    $lozinka=$_POST['lozinka']; 
    $uloga = $_POST['uloga'];
    $nazivLokacije = $_POST['nazivLokacije'];
    $OIB = $_POST['OIBLokacije'];
    $adresa = $_POST['adresaLokacije'];
    $latituda = $_POST['Latituda'];
    $longituda = $_POST['Longituda'];
    $kod =md5(time());
    
    if($uloga=='Korisnik'){
    
        $query= mysqli_query($con, "INSERT INTO korisnik(id_uloga, id_clanstvo, ime_korisnika, prezime_korisnika, email_korisnika, korsnicko_ime, lozinka, status_korisnika, kod_za_registraciju) values (1,1,'$imeKorisnika','$prezime','$emailKorisnika','$korisnickoIme','$lozinka','neaktivan','$kod')");
        
        if($query){
        	$response=new korisnik();
        	$response->success=1;
        	$response->message="successfully registered";
        	
        	$to = $emailKorisnika;
            $subject = "Account activation";
            $message1 = "<a href='https://beervana2020.000webhostapp.com/test/aktivacija_email.php?kod=$kod'>Activate account</a>";
    
    mail($to,$subject,$message1,"From:Beervana2020@gmail.com");
        	die(json_encode($response));
            }
        else{
        	$response=new korisnik();
        	$response->success=0;
        	$response->message="error occured";
        	die(json_encode($response));
        }
    }
    
    
    else{
        $upit= mysqli_query($con, "INSERT INTO korisnik(id_uloga, id_clanstvo, ime_korisnika, prezime_korisnika, email_korisnika, korsnicko_ime, lozinka, status_korisnika, kod_za_registraciju) values(2,2,'$imeKorisnika','$prezime','$emailKorisnika','$korisnickoIme','$lozinka','neaktivan','$kod')");
        if($upit){
            $lokacij = mysqli_query($con,"INSERT INTO lokacija(naziv_lokacije,OIB_lokacije,adresa_lokacije,id_korisnik,longituda,latituda) values('$nazivLokacije','$OIB','$adresa',(SELECT id_korisnik from korisnik order by id_korisnik desc limit 1),$longituda,$latituda)");
            
            if($lokacij){
    	    $response=new korisnik();
    	    $response->success=1;
    	    $response->message="successfully registered";
    	    
    	    
    	    $to = $emailKorisnika;
            $subject = "Account activation";
            $message1 = "<a href='https://beervana2020.000webhostapp.com/test/aktivacija_email.php?kod=$kod'>Activate account</a>";
    
            mail($to,$subject,$message1,"From:Beervana2020@gmail.com");
    	    die(json_encode($response));
            }
            else{
            $response=new korisnik();
        	$response->success=0;
        	$response->message="error occured";
        	die(json_encode($response));
            }
    
    
        }
    }
    
    
    
    
    mysqli_close($con);
    
?>