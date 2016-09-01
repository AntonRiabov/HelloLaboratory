import DS from 'ember-data';

var attr = DS.attr;
export default DS.Model.extend({
  author: DS.belongsTo('author', { async: true }),
  title: attr('String'),
  body: attr('string'),
  date: attr('date')
});

