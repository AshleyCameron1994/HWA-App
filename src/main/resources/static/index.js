'use strict'
//Gets the element and stores it.
const MSealName = document.querySelector('#msealName');
const FSealName = document.querySelector('#fsealName');
const MS_ID = document.querySelector('#ms_Id');
const FS_ID = document.querySelector('#fs_Id');
const MSealWeight = document.querySelector(`#msealWeight`);
const FSealWeight = document.querySelector(`#fsealWeight`);
const FSealM_ID = document.querySelector(`#fsealM_Id`);
const FSealM_Name = document.querySelector(`#fsealM_name`);
const FSealM_Weight = document.querySelector(`#fsealM_weight`);
const MSealName2 = document.querySelector('#msealName2');
const FSealName2 = document.querySelector('#fsealName2');
const MSealWeight2 = document.querySelector(`#msealWeight2`);
const FSealWeight2 = document.querySelector(`#fsealWeight2`);
const FSealM_ID2 = document.querySelector(`#fsealM_Id2`);
const MU_ID = document.querySelector('#MUpdate_Id');
const FU_ID = document.querySelector('#FUpdate_Id');
const MSealDelete = document.querySelector('#msealDelete');
const FSealDelete = document.querySelector('#fsealDelete');

const getMID = () => {
    return [Number.parseFloat(MS_ID.value)];
}
const getFID = () => {
    return [Number.parseFloat(FS_ID.value)];
}

const readOneMSeal = () => {
    fetch(`http://localhost:8081/mseal/read/${MS_ID.value}`, {
        mode: 'no-cors',
        method: `GET`,
        headers: {
            "accept": "application/json",
            "Content-Type": "application/x-www-form-urlencoded"
        },
    })
        .then((response) => response.json())
        .then((json) => console.log(json))
        .catch((err) => console.error(`Stop! ${err}`));
};

const readOneFSeal = () => {
    fetch(`http://localhost:8081/fseal/read/${FS_ID.value}`, {
        mode: 'no-cors',
        method: `GET`,
        headers: {
            "accept": "application/json",
            "Content-Type": "application/x-www-form-urlencoded"
        },
    })
        .then((response) => response.json())
        .then((json) => console.log(json))
        .catch((err) => console.error(`Stop! ${err}`));
};

const findM = () => {
    let ID = getMID();
    readOneMSeal(ID);
}

const findF = () => {
    let ID = getFID();
    readOneFSeal(ID);
}

const readAllMSeal=() => {
    fetch("http://localhost:8081/mseal/readAll", {
        mode: 'no-cors',
        method: 'GET',
        })
            .then((response) => {
                if(response.status != 200){
                    throw new Error("Something went wrong!");
                }else{
                    response.json().then(retrievedinfo => {
                        console.log(retrievedinfo);
                    })
                }
            }).catch((err => console.error("Error please stop what you're doing")))
    
}

const readAllFSeal=() => {
    fetch("http://localhost:8081/fseal/readAll", {
        mode: 'no-cors',    
        method: 'GET',
        })
            .then((response) => {
                if(response.status != 200){
                    throw new Error("Something went wrong!");
                }else{
                    response.json().then(retrievedinfo => {
                        console.log(retrievedinfo);
                    })
                }
            }).catch((err => console.error("Error please stop what you're doing")))
    
}

 
const createMSeal= () => {
    fetch("http://localhost:8081/mseal/create", { 
      method: 'POST',
        body: JSON.stringify({
          "fsealList": [
          ],
          "name": MSealName.value,
          "weight": MSealWeight.value
              }),
              headers : {
                  "Content-Type": "application/json"
              }
        })
            .then((response) => response.json())
            .then((json) => console.log(json))
            .catch((err => console.error("Error please stop what you're doing")))
}

const createFSeal = () => {
    fetch("http://localhost:8081/fseal/create", {  
        method: 'POST',
        body: JSON.stringify({
          "male": {
            "fsealList": [
              null
            ],
            "id": FSealM_ID.value
          },
            "name": FSealName.value,
            "weight": FSealWeight.value
              }),
              headers: {
                  "Content-Type": "application/json"
              }
        })
            .then((response) => response.json())
            .then((json) => console.log(json))
            .catch((err => console.error("Error please stop what you're doing")))
    
}

let updateMSeal = () => {
  fetch("http://localhost:8081/mseal/update/" + MU_ID.value, {
    method: `PUT`,
    body: JSON.stringify({
      "fsealList": [
      ],
      "name": MSealName2.value,
      "weight": MSealWeight2.value
    
    }),
    headers: {
      "Content-Type": "application/json",
    },
  })
    .then((response) => response.json())
    .then((json) => console.log("Success", json))
    .catch((err) => console.error("Error please stop what you're doing"));
};

let updateFSeal = () => {
    fetch("http://localhost:8081/fseal/update/" + FU_ID.value, {
      method: `PUT`,
      body: JSON.stringify({
        "male": {
          "fsealList": [
            null
          ],
          "id": FSealM_ID2.value
        },
          "name": FSealName2.value,
          "weight": FSealWeight2.value
      }),
      headers: {
        "Content-Type":  "application/json",
      },
    })
      .then((response) => response.json())
      .then((json) => console.log(json))
      .catch((err) => console.error("Error please stop what you're doing"));
  };
let deleteMSeal = () => {
  fetch("http://localhost:8081/mseal/delete/" + MSealDelete.value, {
    method: `DELETE`,
    headers: {
      accept: "application/json"
    },
  })
};

let deleteFSeal = () => {
    fetch("http://localhost:8081/fseal/delete/" + FSealDelete.value, {
      method: `DELETE`,
      headers: {
        accept: "application/json"
      }
    })
};