module.exports = function(app) {
  var express = require('express');
  var authorsRouter = express.Router();

  authorsRouter.get('/', function(req, res) {
    res.send(
    /*{
      'authors': */
      [ {
        id: 1,
        name: "Anton",
        posts: ["1", '2']
      }]
    /*}*/
    );
  });

  authorsRouter.post('/', function(req, res) {
    res.status(201).end();
  });

  authorsRouter.get('/:id', function(req, res) {
    res.send({
      'authors':
      {
        id: 1,
        name: "Anton",
        posts: ["1", '2']
      }
    });
  });

  authorsRouter.put('/:id', function(req, res) {
    res.send({
      'authors': {
        id: req.params.id
      }
    });
  });

  authorsRouter.delete('/:id', function(req, res) {
    res.status(204).end();
  });

  app.use('/api/authors', authorsRouter);
};
