db = db.getSiblingDB('ftl-auth-service');
db.createCollection("firstcollection");

db = db.getSiblingDB('sol-stage');
db.createCollection("firstcollection");


// db.createUser({
//     user: "appuser",
//     pwd: "a66UserPassw0rD",
//     roles: [
//         {
//             "role" : "dbAdmin",
//             "db" : "ftl-auth-service"
//         },
//         {
//             "role" : "root",
//             "db" : "admin"
//         },
//         {
//             "role" : "dbOwner",
//             "db" : "ftl-auth-service"
//         },
//         {
//             "role" : "enableSharding",
//             "db" : "ftl-auth-service"
//         },
//         {
//             "role" : "read",
//             "db" : "ftl-auth-service"
//         },
//         {
//             "role" : "readWrite",
//             "db" : "ftl-auth-service"
//         },
//         {
//             "role" : "userAdmin",
//             "db" : "ftl-auth-service"
//         }
//     ]
// });
