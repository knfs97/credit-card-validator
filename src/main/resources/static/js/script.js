const form = document.querySelector('#form_validator');
form.addEventListener('submit', async function (e) {
   e.preventDefault();
   const formData = new FormData(this);
   const data = {
       number : formData.get("card-number"),
       expirationDate : formData.get("expirationDate")
   }

   try {
       const response = await fetch('/api/v1/validator', {
           method: 'POST',
           headers: {
               'Content-Type' : 'application/json; charset=utf-8'
           },
           body: JSON.stringify(data)
       });

       if (response.ok) {
           const data = await response.json();
           alert(data.message)
       }
   } catch (e) {
       console.log(e)
   }

});