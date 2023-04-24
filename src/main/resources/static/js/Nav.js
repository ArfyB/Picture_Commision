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

var cookiearr = cookie.split('; ');

const cookiemap = cookiearr.map(item => {
	const [key, value] = item.split('=');
	return {key, value};
})
var a = cookiemap.find(item => item.key === "a");
if(a === undefined)
{
	
}

/*
if(cookie.split(';').length === 3 )
{
	login_data = cookie.split(';')[2];
	
	UserData = login_data.split('=')[1].split('|');
	const Login = UserData[0];
	const Email = UserData[1];
	const Nick = UserData[2];
	const Role = UserData[3];
	
	console.log(Login);
	console.log(Email);
	console.log(Nick);
	console.log(Role);
	
	$(function()
	{
		var a = $('#logined');
		a.text(Nick);
	})

}
*/

