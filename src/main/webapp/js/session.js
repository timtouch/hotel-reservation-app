class LoginSession{

    constructor(loggedInClient){
        this.loggedInClient = loggedInClient;
    }


    login (){
        getLoginUser();
    }

    logout(){
        this.loggedInClient = null;
        loadHomeNavBar();
        loadHomePage();
    }

    setLoggedInClient(client){
        this.loggedInClient = client;
    }
    getLoggedInClient(){
        return this.loggedInClient;
    }

    isLoggedIn(){
        return loggedInClient !== null && loggedInClient !== undefined;
    }

}

let userSession = new LoginSession({});
