async function getResult(url) {
    try{
        const response = await fetch(url);
        var data = await response.text();
        console.log(data);
        document.getElementById("result").innerHTML = data;
    } catch (e) {
        console.error('Error:', e);
    }
}
// Calling that async function
getResult('Essence_checklist');
