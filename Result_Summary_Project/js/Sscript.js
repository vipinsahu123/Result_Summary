
window.addEventListener("load",()=>{
    const input = document.getElementById("upload");
    const filewrapper =document.getElementById("filewrapper");
    input.addEventListener("change",(e)=>{
        let fileName = e.target.files[0].name;
        let filetype= e.target.value.split(".").pop();
        fileshow(fileName,filetype);
    })

    const fileshow=(fileName, filetype)=>{
        const showfileboxElem=document.createElement("div");
        showfileboxElem.classList.add("showfilebox");
        const leftElem = document.createElement("div");
        leftElem.classList.add("left");
        const fileTypeElem =document.createElement("span");
        fileTypeElem.classList.add("filetype");
        fileTypeElem.innerHTML=filetype;
        leftElem.append(fileTypeElem);
        const filetitleElem = document.createElement("h3");
        filetitleElem.innerHTML=fileName;
        leftElem.append(filetitleElem);
        showfileboxElem.append(leftElem);
        const rightElem = document.createElement("div");
        rightElem.classList.add("right");
        showfileboxElem.append(rightElem);
        const crossElem =document.createElement("span");
        crossElem.innerHTML="&#215;";
        rightElem.append(crossElem);
        filewrapper.append(showfileboxElem);

        crossElem.addEventListener("click",()=>{
            filewrapper.removeChild(showfileboxElem);
        })


    }
    const input1 = document.getElementById("upload1");
    const filewrapper1 =document.getElementById("filewrapper1");
    input1.addEventListener("change",(e)=>{
        let fileName = e.target.files[0].name;
        let filetype= e.target.value.split(".").pop();
        fileshow1(fileName,filetype);
    })

    const fileshow1=(fileName, filetype1)=>{
        const showfilebox1Elem=document.createElement("div");
        showfilebox1Elem.classList.add("showfilebox1");
        const left1Elem = document.createElement("div");
        left1Elem.classList.add("left1");
        const fileType1Elem =document.createElement("span");
        fileType1Elem.classList.add("filetype1");
        fileType1Elem.innerHTML=filetype1;
        left1Elem.append(fileType1Elem);
        const filetitleElem = document.createElement("h3");
        filetitleElem.innerHTML=fileName;
        left1Elem.append(filetitleElem);
        showfilebox1Elem.append(left1Elem);
        const right1Elem = document.createElement("div");
        right1Elem.classList.add("right1");
        showfilebox1Elem.append(right1Elem);
        const crossElem =document.createElement("span");
        crossElem.innerHTML="&#215;";
        right1Elem.append(crossElem);
        filewrapper1.append(showfilebox1Elem);
 
        crossElem.addEventListener("click",()=>{
            filewrapper1.removeChild(showfilebox1Elem);
        })


    }
})


    

