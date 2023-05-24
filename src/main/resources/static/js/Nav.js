var cookie = document.cookie;


/*
';'로 문자열을 split하여 배열로 만듭니다.
const arr = str.split("; ");

key-value 쌍을 만듭니다.
const keyValuePairs = arr.map(item => {
  const pair = item.split("=");
  return {
    key: pair[0],
    value: pair[1]
  };
});
*/

$('.login_user').hide();
$('.dologin').hide();

$(function()
{
	$('#logout').on('click', function()
	{
	location.href='/logout';	
	})	
})


var cookiearr = cookie.split('; ');

const cookiemap = cookiearr.map(item => {
	const [key, value] = item.split('=');
	return {key, value};
})

var logined = cookiemap.find(item => item.key === "login");

if(logined === undefined)
{
	$('.dologin').show();
	$('.login_user').hide();
}
else
{
	$('.dologin').hide();
	$('.login_user').show();
}

/*
if(cookie.split(';').length === 4 )
{
	login_data = cookie.split(';')[3];
	
	UserData = login_data.split('=')[1].split('|');
	const Login = UserData[0];
	const Email = UserData[1];
	const Nick = UserData[2];
	const Role = UserData[3];
	const Tag = UserData[4];
	
	console.log(Login);
	console.log(Email);
	console.log(Nick);
	console.log(Role);
	console.log(Tag);
	
	$(function()
	{
		var a = $('#logined');
		a.text(Nick);
	})

}
*/

