#!/bin/sh
echo '\nCreate three ToDo items'
curl -w " %{http_code}\n" -H "Content-type: application/json" -X POST http://localhost:8080/todo -d '{"done": false, "text": "one"}'
curl -w " %{http_code}\n" -H "Content-type: application/json" -X POST http://localhost:8080/todo -d '{"done": false, "text": "two"}'
curl -w " %{http_code}\n" -H "Content-type: application/json" -X POST http://localhost:8080/todo -d '{"done": false, "text": "three"}'

echo '\nRetrieve the 1st item'
curl -w " %{http_code}\n" -X GET http://localhost:8080/todo/1

echo '\nMark the 1st item to finished'
curl -w " %{http_code}\n" -H "Content-type: application/json" -X PUT http://localhost:8080/todo/1 -d '{"done": true, "text": "one"}'

echo '\nDelete the 2nd item and confirm'
curl -w " %{http_code}\n" -X DELETE http://localhost:8080/todo/2
curl -w " %{http_code}\n" -X GET http://localhost:8080/todo/2

echo '\nFetch all items'
curl -w " %{http_code}\n" -X GET http://localhost:8080/todo
curl -w " %{http_code}\n" -X GET -L "http://localhost:8080/todo?page=1&size=1"

echo '\nFetch items by done-ness'
curl -w " %{http_code}\n" -X GET http://localhost:8080/todo?done=true
curl -w " %{http_code}\n" -X GET http://localhost:8080/todo?done=false
