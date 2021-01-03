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
		</style>
		  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
		  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
		  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	</head>
	<body style="margin: 40;">
		
		<h2>Professors list editing</h2>
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
			  			<td contenteditable='true'> ${entry.getName()}</td>
			  			<td contenteditable='true'>${entry.getAddress()}</td>
			  			<td contenteditable='true'><#if entry.getPhone()??>${entry.getPhone()}</#if></td>
			  			<td contenteditable='true'>${entry.getPayment()} $</td>
			  			<td>
					      	<div class="tile_div">
					      		<form>
					      			<input type=submit value="Update">
					      		</form>
					      		
					      		<form action="/put" method="POST">
						      		<input type=submit value="Delete"> 
						      		<input type="hidden" id="id" name="id" value="${entry.getId()}">
					      		</form>
						    </div>
					      </td>
			  		</tr>
			  	</#list>
			  </table>
			</div>
		</div>
	</body>
</html>
