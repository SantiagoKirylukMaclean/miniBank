# miniBank
Standalone miniBank java app

## Frameworks implemented on this solution

1. Spring Boot (server)
2. Spring (REST)
3. Spring Security (REST)
4. JUnit (testing)
5. Mockito (Testing)
6. Log4j2 (Logs)
7. H2 DB (Database in memory)


## Services


```
URL: /api/auth/signin
METHOD: POST
Request:
{
    "username": "santiago",
    "password": "123456789"
}

Response:
{
    "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzYW50aWFnbyIsImlhdCI6MTU1NDY5NTUyOCwiZXhwIjoxNTU0NzgxOTI4fQ.bG-va8Rjd1IA46VpMQLh4udf9FizOCXJdlN9i9d1RwlMJJmylYNzYn7GuLFBTGnuoV8PYdXi1BF_S-YoE_yo0Q",
    "tokenType": "Bearer"
}
```

```
URL: /balance
METHOD: GET
Request:
Header:
-Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzYW50aWFnbyIsImlhdCI6MTU1NDY5NTUyOCwiZXhwIjoxNTU0NzgxOTI4fQ.bG-va8Rjd1IA46VpMQLh4udf9FizOCXJdlN9i9d1RwlMJJmylYNzYn7GuLFBTGnuoV8PYdXi1BF_S-YoE_yo0Q

Response:
{
    "id": 1,
    "username": "santiago",
    "amount": 5000.43,
    "currency": "ARS"
}
```

```
URL: /transaction/deposit
METHOD: POST
Request:
Header:
-Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzYW50aWFnbyIsImlhdCI6MTU1NDY5NTUyOCwiZXhwIjoxNTU0NzgxOTI4fQ.bG-va8Rjd1IA46VpMQLh4udf9FizOCXJdlN9i9d1RwlMJJmylYNzYn7GuLFBTGnuoV8PYdXi1BF_S-YoE_yo0Q
Body:
{
	"amount":100.00
}

Response:
{
    "id": 1,
    "username": "santiago",
    "amount": 5100.43,
    "currency": "ARS"
}
```

```
URL: /transaction/withdraw
METHOD: POST
Request:
Header:
-Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzYW50aWFnbyIsImlhdCI6MTU1NDY5NTUyOCwiZXhwIjoxNTU0NzgxOTI4fQ.bG-va8Rjd1IA46VpMQLh4udf9FizOCXJdlN9i9d1RwlMJJmylYNzYn7GuLFBTGnuoV8PYdXi1BF_S-YoE_yo0Q
Body:
{
	"amount":5000.00
}

Response:
{
    "id": 1,
    "username": "santiago",
    "amount": 100.43,
    "currency": "ARS"
}
```

```
Only Whit ADMIN Role (Santiago User)
URL: /users
METHOD: GET
Request:
Header:
-Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzYW50aWFnbyIsImlhdCI6MTU1NDY5NTUyOCwiZXhwIjoxNTU0NzgxOTI4fQ.bG-va8Rjd1IA46VpMQLh4udf9FizOCXJdlN9i9d1RwlMJJmylYNzYn7GuLFBTGnuoV8PYdXi1BF_S-YoE_yo0Q

Response:
[
    {
        "id": 1,
        "name": "Santiago",
        "username": "santiago",
        "email": "colo@colo.com",
        "password": "123456789",
        "roles": [
            {
                "id": 1,
                "name": "ROLE_USER"
            },
            {
                "id": 2,
                "name": "ROLE_PM"
            },
            {
                "id": 3,
                "name": "ROLE_ADMIN"
            }
        ]
    },
    {
        "id": 2,
        "name": "Joe",
        "username": "joe",
        "email": "colo@colo.com",
        "password": "123456789",
        "roles": [
            {
                "id": 1,
                "name": "ROLE_USER"
            }
        ]
    }
]
```