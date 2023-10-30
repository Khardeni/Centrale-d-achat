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

var StudentSchema = new Schema ({
     studentName: {type: String },
     studentLastname:{ type: String},
     studentPhone:{ type: String},
     studentClass:{ type: String},
 },{ versionKey: false });

var ProfessorSchema = new Schema ({
    professorFullName:{ type: String},
    professorPhone:{ type: String},
    professorEmail:{ type: String},
 },{ versionKey: false });

 var ClassSchema = new Schema ({
     class:{ type: String},
     professorId:{ type: String},
 },{ versionKey: false });

var studentModel = mongo.model('student', StudentSchema, 'student');
var professorModel = mongo.model('professor', ProfessorSchema, 'professor');
var classModel = mongo.model('class', ClassSchema, 'class');

// var profMod = new professorModel(req.body);
// var studentMod = new studentModel(req.body);
// var classMod = new classModel(req.body);
////////////////////     StudentSection       ////////////////////////

app.post("/api/sv", function(req, res) {
    var studentMod = new studentModel(req.body);
    if(req.body.mode =="Save")  {           /////WORKING
        // studentMod.collection.insertOne(req.body)
        studentMod.save(function(err, data) {
            if(err) {   res.send(data = err);   }
            else{   res.send({data: "Student has been Inserted..!!"});  }
        });
    } else if(req.body.mode =="Update") {
        studentModel.findByIdAndUpdate(req.body.id, {
            studentName: req.body.studentName,
            studentLastname: req.body.studentLastname,
            studentPhone:req.body.studentPhone,
            studentClass: req.body.studentClass},
        function(err, data) {
            if (err) {  res.send(err);  }
            else{   res.send({data: "Student has been Updated..!!"});  }
        });
    } else if(req.body.mode =="ChangeClass") {
        studentModel.findByIdAndUpdate(req.body.id, { studentClass:req.body.studentClass},
          function(err, data) {
              if (err) {    res.send(err);  }
              else{    res.send({data: "Student has been Updated..!!"});   }
          });
    }
})

app.post("/api/sv/deleteStudent",function(req, res) {
    var studentMod = new studentModel(req.body);
    studentMod.remove({ _id: req.body.id }, function(err) {
      if(err) {    res.send(err);   }
    else{   res.send({data: "Student has been Deleted..!!"});   }
});
})


app.get("/api/sv/getStudentsByClass",function(req, res) {
    var studentMod = new studentModel(req.body);
    studentModel.find({studentClass: { $in: [req.body.class]}}, function(err, data){
        if(err) {   res.send(err);  }
        else{   res.send(data); }
    });
})

app.get("/api/sv/getStudentsByPhone",function(req,res) {
    var studentMod = new studentModel(req.body);
    studentModel.find({studentPhone: { $in: [req.body.studentPhone]}}, function(err,data){
        if(err){    res.send(err);  }
        else{   res.send(data); }
    })
})

app.get("/api/sv/SortStudentsByClass",function(req, res) {             /////WORKING
    var studentMod = new studentModel(req.body);
    studentModel.aggregate([{$sort: { studentClass: 1}}], function(err, data){
        if(err) {   res.send(err);  }
        else{   res.send(data); }
    });
})


app.get("/api/sv/Students",function(req, res) {     /////WORKING
    var studentMod = new studentModel(req.body);
    studentModel.find({}, function(err, data){
        if(err) {   res.send(err);  }
        else{   res.send(data); }
    });
})





/*****************************************//////////////////////////*****************************************/


////////////////////     ProfessorSection       ////////////////////////


app.post("/api/pv", function(req, res) {       /////WORKING
    var profMod = new professorModel(req.body);
    if(req.body.mode =="Save")  {
        profMod.save(function(err, data) {
            if(err) {   res.send(data = err);   }
            else{   res.send({data: "Professor has been Inserted..!!"});  }
        });
    } else if(req.body.mode =="Update") {
        professorModel.findByIdAndUpdate(req.body.id, {
            professorFullName: req.body.professorFullName,
            professorPhone: req.body.professorPhone,
            professorEmail:req.body.professorEmail},
        function(err, data) {
            if (err) {  res.send(err);  }
            else{   res.send({data: "Professor has been Updated..!!"});  }
        });
    }

    /*else if(req.body.mode =="ChangeClass") {
        professorModel.findByIdAndUpdate(req.body.id, { done:req.body.studentClass},
          function(err, data) {
              if (err) {    res.send(err);  }
              else{    res.send({data: "Professor has been Updated..!!"});   }
          });
    }*/
})

app.post("/api/pv/deleteProfessor",function(req, res) {
    // var profMod = new professorModel(req.body);
    professorModel.remove({ _id: req.body.id }, function(err) {
      if(err) {    res.send(err);   }
    else{   res.send({data: "Professor has been Deleted..!!"});   }
});
})


app.get("/api/pv/getProfessorByClass",function(req, res) {          /////WORKING
    var profMod = new professorModel(req.body);
    classModel.find({class: { $in: [req.body.class]}},function(err,data){
        if(err) { res.send(err); }
        else{
            console.log(data[0].professorId);
            professorModel.find({_id: { $in: [data[0].professorId]}}, function(err, data){
            if(err) {   res.send(err);  }
                else{   res.send(data); }
            });
        }
    });
})

app.get("/api/pv/getProfessorByPhone",function(req,res) {       //// WORKING
    var profMod = new professorModel(req.body);
    professorModel.find({professorPhone: { $in: [req.body.professorPhone]}}, function(err,data){
        if(err){    res.send(err);  }
        else{   res.send(data); }
    })
})

app.get("/api/pv/SortProfessorClasses",function(req, res) {
    var profMod = new professorModel(req.body);       /////////NOT DONE///////////////////
    professorModel.aggregate([{$sort: { studentClass: 1}}], function(err, data){
        if(err) {   res.send(err);  }
        else{   res.send(data); }
    });
})


app.get("/api/pv/professors",function(req, res) {
    var profMod = new professorModel(req.body);
    professorModel.find({}, function(err, data){
        if(err) {   res.send(err);  }
        else{   res.send(data); }
    });
})

////////////////////     ClassSection       ////////////////////////

app.get("/api/cs/classes",function(req,res){        /////WORKING
    classModel.find({},function(err,data){
        if(err){ res.send(err);}
        else{ res.send(data);}
    })
})

app.get("/api/cs/classStudents",function(req,res){  /////WORKING
    studentModel.find({studentClass: {$in: [req.body.class] }},function(err,data){
        if(err){ res.send(err);}
        else{ res.send(data);}
    })
})

app.post("/api/cs", function(req, res) {
    var classMod = new classModel(req.body);
    if(req.body.mode =="Save")  {       /////WORKING
        classMod.save(function(err, data) {
            if(err) {   res.send(data = err);   }
            else{   res.send({data: "Class has been Inserted..!!"});  }
        });
    } else if(req.body.mode =="Update") {
        classModel.findByIdAndUpdate(req.body.id, {
            professorId: req.body.professorId
        },
        function(err, data) {
            if (err) {  res.send(err);  }
            else{   res.send({data: "Class has been Updated..!!"});  }
        });
    }
})

/////////////////////////////////////////////////////////////////



app.listen(8080, function () {
    console.log('Example app listening on port 8080!');
})
