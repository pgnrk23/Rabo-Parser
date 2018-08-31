'use strict';

var form = document.querySelector('#form');
var fileControl = document.querySelector('#fileControl');
var errorContainer = document.querySelector('#errorContainer');
var successContainer = document.querySelector('#successContainer');

function uploadFile(file) {
    var formData = new FormData();
    formData.append("file", file);

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/processReport");

    xhr.onload = function() {
        var response = JSON.parse(xhr.responseText);
        if(xhr.status == 200) {
            if(response.status.status === 200) {
            	var data = response.data;
            	if(data) {
            		var html = "<table width='100%'><tr><th align='left'><b>Reference</b></th><th align='left'><b>Description</b></th></tr>";
            		for(var record in data) {
            			html +="<tr><td>"+data[record].reference+"</td><td>"+data[record].description+"</td></tr>";
            		}
            		html += "</table>";
	            	successContainer.style.display = "block";
	            	errorContainer.style.display = "none";
	            	successContainer.innerHTML = html;
            	} else {
            		successContainer.style.display = "block";
            		successContainer.innerHTML = "All records processed sucessfully.";
            	}
            } else {
            	successContainer.style.display = "none";
            	errorContainer.style.display = "block";
            	errorContainer.innerHTML = (response.status && response.status.message);
            }
        } else {
            successContainer.style.display = "none";
            errorContainer.innerHTML = (response && response.message) || "Error while processing";
        }
    }

    xhr.send(formData);
}

form.addEventListener('submit', function(event){
    var files = fileControl.files;
    if(files.length === 0) {
        errorContainer.innerHTML = "Please select a file";
        errorContainer.style.display = "block";
    }
    uploadFile(files[0]);
    event.preventDefault();
}, true);