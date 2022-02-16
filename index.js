async function getResult(url) {
    try{
        const response = await fetch(url);
        var data = await response.text();
//        console.log(data);
        document.getElementById("result").innerHTML = data;
    } catch (e) {
        console.error('Error:', e);
    }
}

// readDirectory(directory)
// Calling that async function
//getResult('/history/');
getResult('/history/assessment-fri-feb-11-00-14-37-cet-2022.txt');
