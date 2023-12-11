$(document).ready(function () {
    let mentor = $(".links").find("li").find("#mentor");
    let mentee = $(".links").find("li").find("#mentee");
    let reset = $(".links").find("li").find("#reset");
    let first_input = $("form").find(".first-input");
    let hidden_input = $("form").find(".input__block").find("#repeat__password");
    let login_btn = $("form").find(".login_btn");
  
    //----------- Mentor Login ---------------------
    mentor.on("click", function (e) {
      e.preventDefault();
      $(this).parent().parent().siblings("h1").text("Login");
      $(this).parent().css("opacity", "1");
      $(this).parent().siblings().css("opacity", ".6");
      first_input.removeClass("first-input__block").addClass("signup-input__block");
      hidden_input.css({
        "opacity": "1",
        "display": "block" });
  
      login_btn.text("멘토 로그인");
    });
  
  
    //----------- Mentee Login ---------------------
    mentee.on("click", function (e) {
      e.preventDefault();
      $(this).parent().parent().siblings("h1").text("Login");
      $(this).parent().css("opacity", "1");
      $(this).parent().siblings().css("opacity", ".6");
      first_input.addClass("first-input__block").
      removeClass("signup-input__block");
      hidden_input.css({
        "opacity": "0",
        "display": "none" });
      login_btn.text("멘티 로그인");
    });
  
    //----------- reset ---------------------
    reset.on("click", function (e) {
      e.preventDefault();
      $(this).parent().parent().siblings("form").
      find(".input__block").find(".input").val("");
    });
  });
  //# sourceURL=pen.js