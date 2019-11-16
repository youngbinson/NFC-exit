<?php
if( isset($_GET['user']) )
{
$myfile = fopen("user.txt", "w") ;
fwrite($myfile, $_GET['user']);
fclose($myfile);
}

  $filename = "user.txt";
   $fp = fopen($filename, "r");
 $name=  fgets($fp,1024);
   fclose($fp);


?>

<html>
<head>
<meta http-equiv="refresh" content="1;url=/">
</head>
<body>
<font size="1000">
<?php echo $name; ?>
</font>
</body>
</html>

