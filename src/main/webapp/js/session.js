class LoginSession{

    constructor(loggedInClient){
        this.loggedInClient = loggedInClient;
    }

    /**
     * Logs the client in
     * @param username
     * @param password
     */
    login (){
        this.loggedInClient = getLoginUser();
    }

    logout(){
        this.loggedInClient = null;
    }

    getLoggedInClient(){
        return this.loggedInClient;
    }

    isLoggedIn(){
        return loggedInClient !== null && loggedInClient !== undefined;
    }

}

let userSession = new LoginSession();
