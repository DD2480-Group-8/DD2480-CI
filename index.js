document.write("Text written using JavaScript code!");

fetch('Essence_checklist')
  .then(response => response.text())
  .then(text => console.log(text))
  // outputs the content of the text file