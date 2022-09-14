function getLocator(text){
    var expLoc;
    var loc=document.querySelectorAll('a');
    for (let index = 0; index < loc.length ; index++) {
       if(loc[index].text===text){
           expLoc=loc[index];
           break;
    }
}
    return expLoc;
};

return getLocator('<SpecificText>').click();