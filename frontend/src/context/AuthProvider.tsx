import React, { useState, useEffect } from 'react';
import { createContext, useContext } from 'react';
import { requestClient } from '../api/requestclient';
import { User } from '../type/user';

type AuthContextProps = {
    user?: User;
    email?: string;
    isAuthenticated: boolean;
    login: (username: string, password: string) => Promise<void>,
    logout: () => void;
    checkAuthenticated: () => boolean;
};

const AuthContext = createContext<AuthContextProps>({
    user: undefined,
    email: undefined,
    isAuthenticated: false,
    login: (username, password) => Promise.resolve(),
    logout: () => { },
    checkAuthenticated: () => false
});

const AuthProvider = (props: { children: React.ReactNode }) => {
    const [isAuthenticated, setIsAuthenticated] = useState<boolean>(false);

    useEffect(() => {
        checkAuthenticated()
    }, [])

    const checkAuthenticated = () => {
        const token = localStorage.getItem("at")
        if (token) {
            setIsAuthenticated(true)
            requestClient.setAuthenticationToken(token)
            return true;
        }
        setIsAuthenticated(false)
        return false
    }

    const login = (username: string, password: string) => {
        // Perform authentication logic here
        // Assuming authentication is successful
        console.log(username, password)
        return requestClient.post('/auth/login', { "username": username, "password": password }).then((data) => {
            setIsAuthenticated(true);
            console.log(data);
        })
    };

    const logout = () => {
        // Perform logout logic here
        setIsAuthenticated(false);
    };

    const authContextValue: AuthContextProps = {
        isAuthenticated,
        login,
        logout,
        checkAuthenticated
    };

    return (
        <AuthContext.Provider value={authContextValue}>
            {props.children}
        </AuthContext.Provider>
    );
};

const useAuth = (): AuthContextProps => useContext(AuthContext);

export { useAuth, AuthProvider }