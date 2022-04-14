<script>
  function preventFormSubmit() {
        var forms = document.querySelectorAll('form');
        for (var i = 0; i < forms.length; i++) {
          forms[i].addEventListener('submit', function(event) {
            event.preventDefault();
          });
        }
      }
  window.addEventListener('load', preventFormSubmit);

  function handleFormSubmit(formObject){
    google.script.run.withSuccessHandler(updateUrl).withFailureHandler(onFailure).uploadFiles(formObject);
  }

  function updateUrl(url) {
    var div = document.getElementById('output');
    if(isValidURL(url)){
      div.innerHTML = '<div class="alert alert-success" role="alert"><a href="' + url + '">File uploaded successfully!</a></div>';
      document.getElementById("myForm").reset();
    }else{
      //Show warning message if file is not uploaded or provided
      div.innerHTML = '<div class="alert alert-danger" role="alert">'+ url +'!</div>';
    }
  }

  function onFailure(error) {
    var div = document.getElementById('output');
    div.innerHTML = '<div class="alert alert-danger" role="alert">'+ error.message +'!</div>';
  }

  function isValidURL(string) {
    var res = string.match(/(http(s)?:\/\/.)?(www\.)?[-a-zA-Z0-9@:%._\+~#=]{2,256}\.[a-z]{2,6}\b([-a-zA-Z0-9@:%_\+.~#?&//=]*)/g);
    return (res !== null);
  }
</script>
