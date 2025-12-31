const express = require("express");
const bodyParser = require("body-parser");
const axios = require("axios");

const app = express();
app.use(bodyParser.urlencoded({ extended: true }));

app.get("/", (req, res) => {
  res.send(`
    <h2>Bookstore App</h2>
    <form method="POST" action="/books">
      <input name="name" placeholder="Book Name"/>
      <button>Add Book</button>
    </form>

    <form method="POST" action="/authors">
      <input name="name" placeholder="Author Name"/>
      <button>Add Author</button>
    </form>
  `);
});

app.post("/books", async (req, res) => {
  await axios.post("http://book-service:8080/books", {
    name: req.body.name
  });
  res.redirect("/");
});

app.post("/authors", async (req, res) => {
  await axios.post("http://author-service:8080/authors", {
    name: req.body.name
  });
  res.redirect("/");
});

app.listen(3000, () => {
  console.log("Frontend running on port 3000");
});
