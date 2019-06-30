const emailSignupField = $("#email-signup");
const passwordSignupField = $("#password-signup");
const usernameSignupField = $("#username-signup");
const nameSignupField = $("#name-signup");
const lastNameSignupField = $("#lastname-signup");
const genderSignupField = $('#modal-user-create input[name=gender]:checked');

const registrationForm = {
  email: "",
  password: "",
  username: "",
  name: "",
  lastName: "",
  birthDay: "",
  gender: "",
};

$(document).ready(function () {
  configureModal();
  initMaterializeSelect();
});

function configureModal() {
  $('.modal').modal({
        dismissible: true,
        inDuration: 200,
        outDuration: 100,
        startingTop: '-10%',
        endingTop: '10%'
      }
  );
}

function initMaterializeSelect() {
  $('select').material_select();
}

$("#submit-signup-form").click(onSignupSubmit);

function onSignupSubmit() {
  if (!validateSignupData()) {
    return;
  }

  registrationForm.username = usernameSignupField.val();
  registrationForm.email = emailSignupField.val();
  registrationForm.password = passwordSignupField.val();
  registrationForm.name = nameSignupField.val();
  registrationForm.lastName = lastNameSignupField.val();
  registrationForm.gender = genderSignupField.val();

  sendRegistrationForm();
}

function validateSignupData() {
  if (!isEmailValid()) {
    alert("Please enter correct email!");
    return false;
  }
  if (!isPasswordValid()) {
    alert("Please enter correct password (8 characters required)!");
    return false;
  }
  if (!isUsernameValid()) {
    alert("Please enter correct username!");
    return false;
  }
  if (!isNameAndLastNameValid()) {
    alert("Please enter correct name and last name!");
    return false;
  }
  return true;
}

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

function isUsernameValid() {
  let username = usernameSignupField.val();
  let usernameRegex = new RegExp("[a-zA-Z0-9_\\-]*");

  return usernameRegex.test(username);
}

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

function sendRegistrationForm() {
  let url = "/api/users?_csrf=" + $('input[name=_csrf]').val();
  let data = JSON.stringify(registrationForm);

  $.ajax({
    url: url,
    type: "POST",
    data: data,
    contentType: "application/json; charset=utf-8",
    dataType: "text"
  })
  .done(function () {
    console.log('User created');
    closeModal();
    alert("User created successfully! "
        + "Please verify your email to activate your account");
  })
  .fail(function(err) {
    console.log('Error during create user');
    console.log(err)
  })
}

function closeModal() {
  $('#modal-user-create').modal('close');
}