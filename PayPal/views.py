import paypalrestsdk
from django.http import HttpResponse
from django.http import JsonResponse
from django.views.decorators.csrf import csrf_exempt
@csrf_exempt
def execute(request):
data = request.POST
payment = paypalrestsdk.Payment.find(data["paymentID"])
if payment.execute({"payer_id": data["payerID"]}):
res = "Payment execute successfully"
else:
res = payment.error # Error Hash
return HttpResponse(res)
@csrf_exempt
def create(request):
paypalrestsdk.configure({
"mode": "sandbox", # sandbox or live
"client_id": "xxx",
"client_secret": "xxx"})
payment = paypalrestsdk.Payment({
"intent": "sale",
"payer": {
"payment_method": "paypal"},
"redirect_urls": {
"return_url": "yyy",
"cancel_url": "http://localhost:3000/"},
"transactions": [{
"item_list": {
"items": [{
"name": "item",
"sku": "item",
"price": "5.00",
"currency": "USD",
"quantity": 1}]},
"amount": {
"total": "5.00",
"currency": "USD"},
"description": "This is the payment transaction description."}]})
if payment.create():
return JsonResponse({'paymentID': payment.id})
else:
return HttpResponse(payment.error)