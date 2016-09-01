import Ember from 'ember';

export default Ember.Route.extend({
  model: function(){
    //this.store.findAll('author');
    return this.store.findAll('post');
  }
});
