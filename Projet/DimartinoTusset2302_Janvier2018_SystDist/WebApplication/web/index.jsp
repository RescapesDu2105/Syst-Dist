<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="incl.header.jsp" %>

		<!-- Reference the required client SDKs -->
		<script src="<%= new com.paypal.BtConfigReader().getBtJavaScriptClient() %>"></script>
		<script src="<%= new com.paypal.BtConfigReader().getBtJavaScriptPayPal() %>"></script>
		<script src="<%= new com.paypal.BtConfigReader().getPayPalCheckout() %>"></script>

		<div class="container-fluid">
			<div class="row">

				<div class="col-sm-6 col-md-5">

					<!-- --------- Shopping Cart section --------- -->
				
					<div class="divBorder" style="min-height: 250px;">
						 
                        <h3> Shopping Cart </h3>
                        
                        <!--  Show Shipping option only after PayPal mini-browser session is complete -->
						<table style="width: 100%">
							<tr class="select-shipping" style="display: none;">
								<td style="vertical-align: top;">
									<h4>Ship to:</h4>    				 
				    				<address>
										<strong><span id="addr-recipient"></span></strong><br />
										<span id="addr-line1"></span><br />
										<span id="addr-line2"></span>
										<span id="addr-city"></span> <span id="addr-state"></span> <span id="addr-postal-code"></span><br />
										<span id="addr-country-code"></span>
									</address>
								
								</td>
							</tr>				
						</table>
						
						<br />                   
							
						<table class="table table-striped">
							<tr>
								<th>Quantity</th>
								<th>Description</th>
								<th>Price</th>
							</tr>
							<tr>
								<td>1</td>									
								<td>Demande d'analyses</td>
								<td>6.66</td>
							</tr>
													
							<!--  Show Shipping option only after PayPal mini-browser session is complete -->
							<tr class="select-shipping" style="display: none;">
								<th colspan="2" style="text-align: right;">
									Total
								</th>									
								<td>
									<span id="total-amount-display">6.66</span>
								</td>
							</tr>							
													
						</table>
						
						<br />
						
						<!-- PayPal Checkout button  -->		
						<div style="width: 226px; margin-left:auto; margin-right:auto;" id="papbutton"></div>
						
						<!-- Pay Now button  -->
					 	<div id="pay-now" style="display: none; text-align: center;">
							<form id="bt-payPalButton-form" action="BtCheckoutServlet" method="post">
								<input type="hidden" name="checkout" value="classic" />
								<input type="hidden" name="payment-method-nonce" class="payment-method-nonce" />
								<input type="hidden" name="item-quantity" value="1" />
								<input type="hidden" name="item-description" value="Demande d'analyses" />
								<input type="hidden" name="currency" value="EUR" />
								
								<input type="hidden" id="first-name" name="first-name" value="" />
								<input type="hidden" id="last-name" name="last-name" value="" />
								<input type="hidden" id="recipient" name="recipient" value="" />
								<input type="hidden" id="line1" name="line1" value="" />
								<input type="hidden" id="line2" name="line2" value="" />
								<input type="hidden" id="city" name="city" value="" />
								<input type="hidden" id="state" name="state" value="" />
								<input type="hidden" id="postal-code" name="postal-code" value="" />
								<input type="hidden" id="country-code" name="country-code" value="" />
								
								<input type="hidden" id="item-amount" name="item-amount" value="6.66" />
								<input type="hidden" id="total-amount" name="total-amount" value="6.66" />										
								
								<button type="submit"  style="width: 226px;" id="pay-now" type="button" class="btn btn-primary" onClick="helper();" >Payer maintenant</button>
							</form>
						</div>	
								
						<!-- loader icon while server is processing transaction  -->
						<div style="width: 100%; text-align: center;">
							<div class="loader" style="display: none;"></div>
						</div>					
					</div>
				</div>
	

			</div> <!-- row -->

		</div> <!-- container -->

		<script>
		
			/* 
				Braintree - Initialize the JavaScript client
			*/		
			braintree.client.create({
				
				// authorization from server
				authorization: "<%= new com.paypal.BtTransactionHelper().getToken() %>" 
				
			}, function(clientErr, clientInstance) {
				// Handle error in client creation
				if (clientErr) { 
					console.log('Error creating client instance: ' + clientErr);
					return;
				}
				/* 
					Braintree - PayPal Checkout button component 
				*/				
				braintree.paypalCheckout.create({
					client: clientInstance
				}, function (createErr, paypalCheckoutInstance) {
					if (createErr) {
						if (createErr.code === 'PAYPAL_BROWSER_NOT_SUPPORTED') {
							console.error('This browser is not supported.');
						} 
						else {
							console.error('Error!', createErr);
						}
						return;
					}
					
					// PayPal Checkout button
					paypal.Button.render({
						// sandbox or production
						env: '<%= new com.paypal.BtConfigReader().getEnvironment() %>', 
						locale: 'en_US',
						
						style: {
							size: 'medium',   // tiny | small | medium
							color: 'gold',	// gold | blue | silver
							shape: 'pill',	// pill | rect
							label: 'checkout' // checkout | credit
						},
						payment: function () {
														
							// update the payment flow image to the PayPal mini-browser
							$("#flow-step").attr("src","img/classic-flow-2.png");
							
							// remove Second flow button (labeled "Proceed to Shipping")
							$('#second-flow').css('display', 'none');
							
							// remove the hover-over message
							$('#initial-message').css('display', 'none');
							
							return paypalCheckoutInstance.createPayment({
								flow: 'checkout',
								intent: 'sale',
								amount: '6.66',
								currency: 'EUR',
								locale: 'fr_FR',
								displayName: 'Application finale',
								enableShippingAddress: true,
								shippingAddressEditable: true
							});
						},
						
						commit: false, // PayPal mini-browser button: true (Pay Now button) | false (Continue button)
						
						onAuthorize: function (data, actions) {
						
							// data: intent, payerID, paymentID, paymentToken, returnUrl
							console.log('onAuthorize (data): ', JSON.stringify(data, 0, 2));
							
							return paypalCheckoutInstance.tokenizePayment(data, function (err, payload) {
								
								// payload: nonce, type, countryCode, email, firstName, lastName, payerId, shippingAddress
								console.log('onAuthorize (payload): ', JSON.stringify(payload, 0, 2));
								
								// extract shipping address and place into form
								parseDetails(payload.details);
								if (err) {
									console.log('onAuthorize (err): ', JSON.stringify(err, 0, 2));
								} 
								else {
									
									// remove Express Checkout button
									$('#papbutton').css('display', 'none');
									
									// 	Put `payload.nonce` into the 'payment-method-nonce' input
									$('.payment-method-nonce').val(payload.nonce);
									
									// update payment flow diagram, highlight Pay Now
									$("#flow-step").attr("src","img/classic-flow-3.png");
									
									// Show shipping option
									$('.select-shipping').css('display', 'table-row');
									
									// Show "Pay Now" button
									$('#pay-now').css('display', 'inline');
								}
							});
						},
						
						onCancel: function (data) {
							console.log('onCancel: ', JSON.stringify(data, 0, 2));
							var currentUrl = window.location.protocol + '//' + window.location.host + window.location.pathname;
							var cancelUrl = currentUrl.substring(0, currentUrl.lastIndexOf('/')) + '/cancel.jsp';
							window.location.href = cancelUrl;
						},
						
						onError: function (err) {
							console.log('onError: ', JSON.stringify(err, 0, 2));
							var currentUrl = window.location.href = window.location.protocol + '//' + window.location.host + window.location.pathname;
							var errorUrl = currentUrl.substring(0, currentUrl.lastIndexOf('/')) + '/error.jsp?message=' + encodeURIComponent(err.toString());
							window.location.href = errorUrl;
						}
					}, '#papbutton'); // the PayPal Checkout button will be rendered in an html element with the id `papbutton`
				});	
			});
			
			
		</script>
		
		<script>
		
			// button hovers
			$("#papbutton").mouseover(function(){
				$("#flow-step").attr("src","img/classic-flow-1.png");
			});
			
			$("#continue").mouseover(function(){
				$("#flow-step").attr("src","img/pay-now-flow-1.png");
			});
		
						
			// extract shipping information from tokenizePayment response
			function parseDetails(details) {
				$("#first-name").val(details.firstName);
				$("#last-name").val(details.lastName);
				$("#recipient").val(details.shippingAddress.recipientName);
				$("#line1").val(details.shippingAddress.line1);
				$("#line2").val(details.shippingAddress.line2);
				$("#city").val(details.shippingAddress.city);
				$("#state").val(details.shippingAddress.state);
				$("#postal-code").val(details.shippingAddress.postalCode);
				$("#country-code").val(details.shippingAddress.countryCode);
				
				
				document.getElementById("addr-recipient").innerHTML = details.shippingAddress.recipientName;
				
				document.getElementById("addr-line1").innerHTML = details.shippingAddress.line1;
				document.getElementById("addr-line2").innerHTML = 
					(typeof details.shippingAddress.line2 === 'undefined') ? 
							document.getElementById("addr-line2").innerHTML = '' : 
							details.shippingAddress.line2 + '<br />';
				document.getElementById("addr-city").innerHTML = details.shippingAddress.city;
				document.getElementById("addr-state").innerHTML = details.shippingAddress.state;
				document.getElementById("addr-postal-code").innerHTML = details.shippingAddress.postalCode;
				document.getElementById("addr-country-code").innerHTML = details.shippingAddress.countryCode;
				
			}	
			
			// "Pay Now" button clicked - hide button and show spinner while transaction is processing 
			function helper() {
				
				// Hide Pay now
				$('#pay-now').css('display', 'none');
				
				// show spinner
				$('.loader').css('display', 'inline-block');
			}
		
		</script>


<%@ include file="incl.footer.jsp" %>
