# ROLE WITH PERMISSION THROUGH SPRING SECURITY IN SPRING BOOT

<p align="center">
    <img src="screenshots/spring_boot_role_permission_main_image.png" alt="Main Information" width="700" height="500">
</p>

### 📖 Information

<ul style="list-style-type:disc">
  <li><b>This</b> is a Spring Boot example covering important and useful features.</li>
  <li>Here is an explanation of the example:</li>
       <ul>
         <li><b>Admin</b> and <b>User</b> implement their own <b>authentication</b> and <b>authorization</b> through their defined <b>role</b> names.</li>
         <li>The <b>Admin</b> handles with the following process shown above:
            <ul>
              <li><b>Admin</b> with <b>Role</b> containing <b>create</b> permission only handles with creating product</li>
              <li><b>Admin</b> with <b>Role</b> containing <b>get</b> permission only handles with getting product by id</li>
              <li><b>Admin</b> with <b>Role</b> containing <b>update</b> permission only handles with updating product by id</li>
              <li><b>Admin</b> with <b>Role</b> containing <b>delete</b> permission only handles with deleting product by id</li>
            </ul>
         </li>
         <li>The <b>User</b> handles with the following process shown above:
            <ul>
              <li><b>User</b> with <b>Role</b> containing <b>get</b> permission only handles with getting product by id</li>
            </ul>
         </li>
  </ul>
</ul>


### Explore Rest APIs

<table style="width:100%">
  <tr>
      <th>Method</th>
      <th>Url</th>
      <th>Description</th>
      <th>Request Body</th>
      <th>Header</th>
      <th>Valid Path Variable</th>
      <th>No Path Variable</th>
  </tr>
  <tr>
      <td>POST</td>
      <td>/api/v1/authentication/user/register</td>
      <td>User Register</td>
      <td>RegisterRequest</td>
      <td></td>
      <td></td>
      <td></td>
  <tr>
  <tr>
      <td>POST</td>
      <td>/api/v1/authentication/user/login</td>
      <td>User Login</td>
      <td>LoginRequest</td>
      <td></td>
      <td></td>
      <td></td>
  <tr>
  <tr>
      <td>POST</td>
      <td>/api/v1/authentication/user/refresh-token</td>
      <td>User Refresh Token</td>
      <td>TokenRefreshRequest</td>
      <td></td>
      <td></td>
      <td></td>
  <tr>
  <tr>
      <td>POST</td>
      <td>/api/v1/authentication/user/logout</td>
      <td>User Logout</td>
      <td>TokenInvalidateRequest</td>
      <td></td>
      <td></td>
      <td></td>
  <tr>
  <tr>
      <td>POST</td>
      <td>/api/v1/products</td>
      <td>Create Product</td>
      <td>ProductCreateRequest</td>
      <td></td>
      <td></td>
      <td></td>
  <tr>
  <tr>
      <td>GET</td>
      <td>/api/v1/products/{productId}</td>
      <td>Get Product By Id</td>
      <td></td>
      <td></td>
      <td>ProductId</td>
      <td></td>
  <tr>
  <tr>
      <td>GET</td>
      <td>/api/v1/products</td>
      <td>Get Products</td>
      <td>ProductPagingRequest</td>
      <td></td>
      <td></td>
      <td></td>
  <tr>
  <tr>
      <td>PUT</td>
      <td>/api/v1/products/{productId}</td>
      <td>Update Product By Id</td>
      <td>ProductUpdateRequest</td>
      <td></td>
      <td>ProductId</td>
      <td></td>
  <tr>
  <tr>
      <td>DELETE</td>
      <td>/api/v1/products/{productId}</td>
      <td>Delete Product By Id</td>
      <td></td>
      <td></td>
      <td>ProductId</td>
      <td></td>
  <tr>
</table>


### Technologies

---
- Java 21
- Spring Boot 3.0
- Restful API
- Lombok
- Maven
- Junit5
- Mockito
- TestContainer
- Integration Tests
- Docker
- Docker Compose
- CI/CD (Github Actions)
- Postman
- Spring Boot Open Api


### Postman

```
Import postman collection under postman_collection folder
```

### Open Api

```
http://localhost:1225/swagger-ui/index.html
```

### Prerequisites

#### Define Variable in .env file

```
DATABASE_USERNAME={DATABASE_USERNAME}
DATABASE_PASSWORD={DATABASE_PASSWORD}
```

---
- Maven or Docker
---


### Docker Run
The application can be built and run by the `Docker` engine. The `Dockerfile` has multistage build, so you do not need to build and run separately.

Please follow directions shown below in order to build and run the application with Docker Compose file;

```sh
$ cd rolepermissionexample
$ docker-compose up -d
```

If you change anything in the project and run it on Docker, you can also use this command shown below

```sh
$ cd rolepermissionexample
$ docker-compose up --build
```

---
### Maven Run
To build and run the application with `Maven`, please follow the directions shown below;

```sh
$ cd rolepermissionexample
$ mvn clean install
$ mvn spring-boot:run
```

### Screenshots

<details>
<summary>Click here to show the screenshots of project</summary>
    <p> Figure 1 </p>
    <img src ="screenshots/1.PNG">
    <p> Figure 2 </p>
    <img src ="screenshots/2.PNG">
    <p> Figure 3 </p>
    <img src ="screenshots/3.PNG">
    <p> Figure 4 </p>
    <img src ="screenshots/4.PNG">
    <p> Figure 5 </p>
    <img src ="screenshots/5.PNG">
    <p> Figure 6 </p>
    <img src ="screenshots/6.PNG">
    <p> Figure 7 </p>
    <img src ="screenshots/7.PNG">
</details>

### Contributors

- [Sercan Noyan Germiyanoğlu](https://github.com/Rapter1990)