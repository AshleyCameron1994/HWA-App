'use strict'
//Gets the element and stores it.
const MSealName = document.querySelector('#msealName');
const FSealName = document.querySelector('#fsealName');
const MS_ID = document.querySelector('#ms_Id');
const FS_ID = document.querySelector('#fs_Id');
const MSealWeight = document.querySelector(`#msealWeight`);
const FSealWeight = document.querySelector(`#fsealWeight`);
const FSealM_ID = document.querySelector(`#fsealM_id`);
const MU_ID = document.querySelector('MUpdate_Id');
const FU_ID = document.querySelector('#FUpdate_Id');


MGETURL = "http://localhost:8081/mseal/readAll";
FGETURL = "http://localhost:8081/fseal/readAll";
MGETONEL = "http://localhost:8081/mseal/read/";
FGETONEURL = "http://localhost:8081/fseal/read/";
MPOSTURL = "http://localhost:8081/mseal/create";
FPOSTURL = "http://localhost:8081/fseal/create";
MDELETEURL = "http://localhost:8081/mseal/delete/";
FDELETEURL = "http://localhost:8081/fseal/delete/";
MPUTURL = "http://localhost:8081/mseal/update/";
FPUTURL = "http://localhost:8081/fseal/update/";

const getMID = () => {
    return [Number.parseFloat(MS_ID.value)];
}
const getFID = () => {
    return [Number.parseFloat(FS_ID.value)];
}

const readOneMSeal = () => {
    fetch(MGETONEURL + MS_ID.value, {
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
    fetch(FGETONEURL + FS_ID.value, {
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
    fetch(MGETURL, {
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
    fetch(FGETURL, {
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
    fetch(MPOSTURL, {
        method: 'POST',
        body: JSON.stringify({
                "name": MSealName.value,
                "weight": MSealWeight.value
              }),
              headers: {
                  "Content-Type": "application/json"
              }
        })
            .then((response) => response.json())
            .then((json) => console.log(json))
            .catch((err => console.error("Error please stop what you're doing")))
    
}

const createFSeal = () => {
    fetch(MPOSTURL, {
        method: 'POST',
        body: JSON.stringify({
                "name": FSealName.value,
                "weight": MSealWeight.value,
                "male_id": FSealM_ID.value
              }),
              headers: {
                  "Content-Type": "application/json"
              }
        })
            .then((response) => response.json())
            .then((json) => console.log(json))
            .catch((err => console.error("Error please stop what you're doing")))
    
}
// const id = document.querySelector(`#id`);
// const name = document.querySelector(`#name`);
// const status = document.querySelector(`#status`);

let updateMSeal = () => {
  fetch(MPUTURL + MU_ID.value, {
    method: `POST`,
    body: JSON.stringify({
      "name": name.value,
      status: status.value,
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
    fetch(FPUTURL + FU_ID.value, {
      method: `POST`,
      body: JSON.stringify({
        name: name.value,
        status: status.value,
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
  fetch(MDELETEURL + , {
    method: `DELETE`,
    body: JSON.stringify({
      id: 1,
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
    fetch(FDELETEURL + , {
      method: `DELETE`,
      body: JSON.stringify({
        id: 1,
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