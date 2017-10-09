let emailSignupField = $("#email-signup");
let passwordSignupField = $("#password-signup");
let usernameSignupField = $("#username-signup");
let nameSignupField = $("#name-signup");
let lastNameSignupField = $("#lastname-signup");

$(document).ready(function () {
  $('.modal').modal({
        dismissible: true,
        inDuration: 200,
        outDuration: 100,
        startingTop: '-10%',
        endingTop: '10%'
      }
  );

  $('select').material_select();

  $('.datepicker').pickadate({
    selectMonths: true,
    format: 'dd-mm-yyyy',
    formatSubmit: 'yyyy-mm-dd',
    selectYears: 120,
    min: [1900, 1, 1],
    today: 'Today',
    clear: 'Clear',
    close: 'Ok',
    closeOnSelect: false
  });

  console.log("modal ready");
});

function isEmailValid() {
  let email = emailSignupField.val();
  let emailRegex = new RegExp(
      "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?");
  return emailRegex.test(email);
}

function isPasswordValid() {
  let password = passwordSignupField.val();
  let passwordRegex = new RegExp("(.{8,64})");
  return passwordRegex.test(password);
}

function showNextFields() {
  $("#signup-first-form").hide(300);
  $("#next-signup-form").hide(100);

  $("#signup-second-form").show(300);
  $("#submit-signup-form").show(300);

}

$("#next-signup-form").click(function () {

  if (!isEmailValid()) {
    alert("Invalid email: " + passwordSignupField.val());
  } else if (!isPasswordValid()) {
    alert("Please enter correct password (8 characters required)!");
  } else {
    showNextFields();
    registrationForm.email = emailSignupField.val();
    registrationForm.password = passwordSignupField.val();
  }
})

function isNameAndLastNameValid() {
  let name = nameSignupField.val();
  let lastName = lastNameSignupField.val();

  if (name.length < 2 || name.length > 20) {
    return false;
  }
  if (lastName.length < 2 || lastName.length > 30) {
    return false;
  }
  return true;
}

function isUsernameValid() {
  let username = usernameSignupField.val();
  let usernameRegex = new RegExp("[a-zA-Z0-9_\\-]*");

  return usernameRegex.test(username);
}

function isMaleChecked() {
  if ($("#female").is(":checked")) {
    return false;
  } else {
    return true;
  }
}

function sendRegistrationForm() {
  let url = "/api/users?_csrf=" + $('input[name=_csrf]').val();
  let s = JSON.stringify(registrationForm);
  let data = s;

  $.ajax({
    type: "POST",
    url: url,
    data: data,
    success: function (data) {
      console.log("success: " + data.status);
    },
    error: function (err) {
      console.log("error " + err.status + " ms: " + err);
    },
    dataType: "json",
    contentType: "application/json;charset=utf-8"
  });
  console.log("sent");
}

$("#submit-signup-form").click(function () {
  if (!isUsernameValid()) {
    alert("Invalid username!");
  } else {
    registrationForm.username = usernameSignupField.val();
  }
  if (!isNameAndLastNameValid()) {
    alert("Invalid name or last name!");
  } else {
    registrationForm.name = nameSignupField.val();
    registrationForm.lastName = lastNameSignupField.val();
    registrationForm.male = isMaleChecked();

    sendRegistrationForm();
  }
})

var registrationForm = {
  email: "",
  password: "",
  username: "",
  name: "",
  lastName: "",
  birthDay: "",
  male: false
};