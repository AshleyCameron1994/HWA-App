'use strict'
//Gets the element and stores it.
const MSealName = document.querySelector('#msealName');
const FSealName = document.querySelector('#fsealName');
const MS_ID = document.querySelector('#ms_Id');
const FS_ID = document.querySelector('#fs_Id');
const MSealWeight = document.querySelector(`#msealWeight`);
const FSealWeight = document.querySelector(`#fsealWeight`);
const FSealM_ID = document.querySelector(`#fsealM_id`);
const MSealName2 = document.querySelector('#msealName2');
const FSealName2 = document.querySelector('#fsealName2');
const MSealWeight2 = document.querySelector(`#msealWeight2`);
const FSealWeight2 = document.querySelector(`#fsealWeight2`);
const FSealM_ID2 = document.querySelector(`#fsealM_id2`);
const MU_ID = document.querySelector('MUpdate_Id');
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
      mode: 'no-cors',  
      method: 'POST',
        body: JSON.stringify({
              "fsealList": [],
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
        mode: 'no-cors',  
        method: 'POST',
        body: JSON.stringify({
          "male": {
            "id": FSealM_ID,
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
  fetch(`http://localhost:8081/mseal/update/${MU_ID.value}`, {
    mode: 'no-cors',
    method: `POST`,
    body: JSON.stringify({
      "fsealList": [],
      "name": MSealName2.value,
      "weight": MSealWeight2.value
    }),
    headers: {
      accept: "application/json",
      "Content-Type": "application/x-www-form-urlencoded",
    },
  })
    .then((response) => response.json())
    .then((json) => console.log(json))
    .catch((err) => console.error(`Stop! ${err}`));
};

let updateFSeal = () => {
    fetch(`http://localhost:8081/fseal/update/${FU_ID.value}`, {
      mode: 'no-cors',
      method: `POST`,
      body: JSON.stringify({
        "male": {
          "id": FSealM_ID2
        },
        "name": FSealName2.value,
        "weight": FSealWeight2.value,
      }),
      headers: {
        accept: "application/json",
        "Content-Type": "application/x-www-form-urlencoded",
      },
    })
      .then((response) => response.json())
      .then((json) => console.log(json))
      .catch((err) => console.error(`Stop! ${err}`));
  };
let deleteMSeal = () => {
  fetch(`http://localhost:8081/fseal/delete/${MSealDelete.value}`, {
    mode: 'no-cors',
    method: `DELETE`,
    body: JSON.stringify({
      id: MSealDelete.value,
    }),
    headers: {
      accept: "application/json",
      api_key: 1,
    },
  })
    .then((response) => response.json())
    .then((json) => console.log(json))
    .catch((err) => console.error(`Stop! ${err}`));
};

let deleteFSeal = () => {
    fetch(`http://localhost:8081/fseal/delete/${FSealDelete.value}`, {
      mode: 'no-cors',
      method: `DELETE`,
      body: JSON.stringify({
        id: FSealDelete.value,
      }),
      headers: {
        accept: "application/json",
        api_key: 1,
      },
    })
      .then((response) => response.json())
      .then((json) => console.log(json))
      .catch((err) => console.error(`Stop! ${err}`));
  };