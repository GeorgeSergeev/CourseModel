<html>
	<head>
		<style>
			table {
			  border-collapse: collapse;
			  width: 100%;
			}
			
			th, td {
			  text-align: left;
			  padding: 8px;
			}
			
			tr:nth-child(even) {background-color: #f2f2f2;}
			
			.tile_div input {
			    display: block;
			    float: left;
			    height: 40%;
			    width: 40%;
			    margin-right: 5px;
			    text-align: center;
			    line-height: 50px;
			    text-decoration: none;
			}
			
			.title_div input.last {
			    margin-right: 0;
			}
			
			.clear {
			    clear: both;
			}
			
			.button {
			  display: inline-block;
			  padding: 5px 10px;
			  font-size: 15px;
			  cursor: pointer;
			  text-align: center;
			  text-decoration: none;
			  outline: none;
			  color: #fff;
			  background-color: #4CAF50;
			  border: none;
			  border-radius: 15px;
			}
			
			.button:hover {background-color: #3e8e41}
			
			.button:active {
			  background-color: #3e8e41;
			  transform: translateY(4px);
}
		</style>
		  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
		  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
		  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	</head>
	<body style="margin: 40;">
		
		<h2>Editing list of professors</h2>
		<p>To edit value, press twice the filed, remove previous and put your new one. When you're done, click update button to synchronize your data with database. </p>
		
		<div style="margin-left: 100; margin-right: 100">
			<div style="overflow-x:auto;">
			  <table>
			  
			    <tr>
			      <th>Professor's name</th>
			      <th>Address</th>
			      <th>Phone</th>
			      <th>Payment</th>
			      <th>Actions</th>
			    </tr>
			    
			    <#list data as entry>
			  		<tr>
			  			<td id="name_${entry.getId()}" contenteditable='true'> ${entry.getName()}</td>
			  			<td id="addr_${entry.getId()}" contenteditable='true'>${entry.getAddress()}</td>
			  			<td id="phone_${entry.getId()}" contenteditable='true'><#if entry.getPhone()??>${entry.getPhone()}</#if></td>
			  			<td id="payment_${entry.getId()}" contenteditable='true'>${entry.getPayment()}</td>
			  			<td>
					      	<div class="tile_div">
					      		<button onClick="updateProfessor(${entry.getId()})" class="button">Update</button>
					      		<button onClick="deleteProfessor(${entry.getId()})" class="button" style="background-color: #FF0000;">Delete</button>
						    </div>
					      </td>
			  		</tr>
			  	</#list>
			  </table>
			</div>
		</div>
		
		
		<script>
   			function updateProfessor(id){
   				var name = document.getElementById("name_" + id).innerText;
   				var addr = document.getElementById("addr_" + id).innerText;
   				var phone = document.getElementById("phone_" + id).innerText;
   				var payment = parseFloat(document.getElementById("payment_" + id).innerText.replace(/,/, '.'));
   				
   				console.log(name + " " + addr + " " + phone + " " + payment);
   				
   				var http = new XMLHttpRequest();
   				var params = 'id=' + id +'&name=' + name + '&address=' + addr + '&phone=' + phone + '&payment=' + payment;
   				
   				http.open('POST', '/putProfessor', true);
   				http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
   				http.onreadystatechange = function() {
   				    if(http.readyState == 4 && http.status == 200) {
   				    	document.location.reload();
   				    }
   				}
   				http.send(params);
   			}
   			
   			function deleteProfessor(id){
   				
   				var http = new XMLHttpRequest();
   				var params = 'id=' + id;
   				
   				http.open('POST', '/removeProfessor', true);
   				http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
   				http.onreadystatechange = function() {
   				    if(http.readyState == 4 && http.status == 200) {
   				    	document.location.reload();
   				    }
   				}
   				http.send(params);
   			}
   		</script>
	</body>
</html>
