var express = require('express');
var path = require("path");
var bodyParser = require('body-parser');
var mongo = require("mongoose");

mongo.set('strictQuery', true);
var db = mongo.connect("mongodb://127.0.0.1:27017/todo", function(err, response) {
    if(err) { console.log(err); }
    else{ console.log('Connected to ' + db, ' + ', response); }
});


var app = express()
app.use(bodyParser());
app.use(bodyParser.json({limit:'5mb'}));
app.use(bodyParser.urlencoded({extended: true}));


app.use(function (req, res, next) {
    res.setHeader('Access-Control-Allow-Origin', 'http://localhost:4200');
    res.setHeader('Access-Control-Allow-Methods', 'GET, POST, OPTIONS, PUT, PATCH, DELETE');
    res.setHeader('Access-Control-Allow-Headers', 'X-Requested-With, content-type');
    res.setHeader('Access-Control-Allow-Credentials', true);
    next();
});

    var Schema = mongo.Schema;

var ToDosSchema = new Schema ({
     name: {type: String },
     priority:{ type : Number},
     term:{ type : String},
     done:{ type : Number},
  },{ versionKey: false });

var model = mongo.model('todo', ToDosSchema, 'todo');

app.post("/api/SaveTodo", function(req, res) {
    var mod = new model(req.body);
    if(req.body.mode =="Save")
    {
        mod.save(function(err, data) {
            if(err) {
                res.send(data = err);
            }
            else{
                res.send({data: "todo has been Inserted..!!"});
            }
        });
    }
    else if(req.body.mode =="Update")
    {
      model.findByIdAndUpdate(req.body.id, { name: req.body.name, priority: req.body.priority, term:req.body.term},
        function(err, data) {
            if (err) {
            res.send(err);
            }
            else{
                res.send({data: "todo has been Updated..!!"});
            }
        });
    }
    else if(req.body.mode =="ChangeStatus")
    {
        model.findByIdAndUpdate(req.body.id, { done:req.body.done},
          function(err, data) {
              if (err) {
              res.send(err);
              }
              else{
                  res.send({data: "todo has been Updated..!!"});
              }
          });
    }
})

app.post("/api/deleteTodo",function(req, res) {
    model.remove({ _id: req.body.id }, function(err) {
      if(err) {
        res.send(err);
    }
    else{
        res.send({data: "todo has been Deleted..!!"});
    }
});
})


app.get("/api/getSmallTodos",function(req, res) {
    model.find({term: { $in: ['Short','short']}}, function(err, data){
        if(err) {
            res.send(err);
        }
        else{
            res.send(data);
        }
    });
})

app.get("/api/getSortedTodos",function(req, res) {
    model.aggregate([{$sort: { priority: 1}}], function(err, data){
        if(err) {
            res.send(err);
        }
        else{
            res.send(data);
        }
    });
})


app.get("/api/getTodos",function(req, res) {
    model.find({}, function(err, data){
        if(err) {
            res.send(err);
        }
        else{
            res.send(data);
        }
    });
})


app.listen(8080, function () {
    console.log('Example app listening on port 8080!');
})
