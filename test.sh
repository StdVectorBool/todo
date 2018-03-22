#!/bin/sh
curl -w " %{http_code}\n" -H "Content-type: application/json" -X POST http://localhost:8080/todo -d '{"done": false, "text": "blah"}'
curl -w " %{http_code}\n" -X GET http://localhost:8080/todo/1
curl -w " %{http_code}\n" -H "Content-type: application/json" -X PUT http://localhost:8080/todo/1 -d '{"done": false, "text": "blah"}'
curl -w " %{http_code}\n" -X DELETE http://localhost:8080/todo/1
curl -w " %{http_code}\n" -X GET http://localhost:8080/todo
