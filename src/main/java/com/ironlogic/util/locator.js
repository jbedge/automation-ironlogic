async function getLocator(text){
    while(true){
    var expLoc;
    await new Promise(r=>setTimeout(r,2000));
    var loc=document.querySelectorAll('button');
    for (let index = 0; index < loc.length ; index++) {
//    console.log(loc[index].getAttribute("id"))
       if(loc[index].getAttribute("id")===text){
           expLoc=loc[index];
           expLoc.click();
           console.log(loc[index].getAttribute("id"))
           break;
    }
  }
 }
};

getLocator('ip-no');