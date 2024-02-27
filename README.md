# customer-experiment-service
Welcome to the Customer Experience Service (or CES for short)

This is a simple application that allows us to track the experiments that might be assigned to a customer, 
for example we might experiment the colour of the website and want to make sure we keep track of that. 
This app enables this by exposing HTTP endpoints to:
1. Create experiments
2. List all experiments
3. Delete experiments
4. Assign an anonymous user to an experiment
5. Assign a user to an experiment by Id

## Running the application: 
Docker TODO


## Interface
### Create an experiment
endpoint:
    `POST localhost:8080/experiment`
#### request
`{
    "name": "colour",
    "type": "red"
}`
#### response
`{
"experimentId": "4fabbf99-20fa-4957-8c3c-8d46eeadea87"
}`
-------
### Get all experiments
endpoint: `GET localhost:8080/experiment`
#### response
```json
[
    {
        "id": "8fda77e3-f909-41e3-a6fc-63c25334de09",
        "date": "2024-02-27T16:16:43.801+00:00",
        "name": "colour",
        "type": "brown"
    },
    {
        "id": "f298c447-3917-446d-acdd-c8f7a123c27a",
        "date": "2024-02-27T16:16:43.801+00:00",
        "name": "colour",
        "type": "yellow"
    },
    {
        "id": "4fabbf99-20fa-4957-8c3c-8d46eeadea87",
        "date": "2024-02-27T16:16:44.502+00:00",
        "name": "colour",
        "type": "red"
    }
]
```
-------
### Delete an experiment

endpoint: `DELETE localhost:8080/experiment/{id}`
#### response
```json
{
    "deleted": true,
    "removedFrom": 1,
    "id": "8fda77e3-f909-41e3-a6fc-63c25334de09"
}
```
-------
### Assign an experiment to an anonymous user
endpoint:
`GET localhost:8080/experiment/user`
#### response
```json
{
    "date": "2024-02-27T16:20:03.434+00:00",
    "output": [
        {
            "id": "4fabbf99-20fa-4957-8c3c-8d46eeadea87",
            "date": "2024-02-27T16:16:44.502+00:00",
            "name": "colour",
            "type": "red"
        }
    ],
    "userId": "d26db20b-c1f6-4ede-b2d5-10eba9fb87fd"
}
```
-------
### Assign an experiment to a user
endpoint:
`GET localhost:8080/experiment/user/{id}`
#### response
```json
{
    "date": "2024-02-27T16:21:28.533+00:00",
    "output": [
        {
            "id": "32a82a81-5dba-434b-8ce2-7bda3343702d",
            "date": "2024-02-27T16:16:43.801+00:00",
            "name": "colour",
            "type": "brown"
        }
    ],
    "userId": "123"
}
```