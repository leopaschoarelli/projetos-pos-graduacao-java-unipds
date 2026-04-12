"use client";

import Cookies from "js-cookie";
import { createContext, useState, useContext, useEffect } from "react";
import jwt from 'jsonwebtoken';

export type User = {
    email: string;
    role: "user" | "admin";
};

type AuthContextProps = {
    user: User | null;
    token: string | null;
    login: (email: string, password: string) => Promise<void>;
    logout: () => void;
}

const AuthContext = createContext({} as AuthContextProps);

export const AuthProvider = ({ children }: { children: React.ReactNode }) => {
    const [token, setToken] = useState<string | null>(null);
    const [user, setUser]   = useState<User | null>(null);

    useEffect(() => {
        const savedToken = Cookies.get("token");

        if (savedToken) {
            const {email, role} = jwt.decode(savedToken) as unknown as User;
            setToken(savedToken);
            setUser({ email, role });
        }
    }, []);

    const login = async (email: string, password: string) => {
        const res = await fetch("/api/auth", {
            method: "POST",
            body: JSON.stringify({ email, password }),
            headers: {
                'Content-Type': 'application/json',
            },
            credentials: 'include', // importante!
        });

        const data = await res.json();


        if (res.ok) {
            setUser(data.user);
        } else {
            throw new Error(data.message);
        };
    };

    const logout = () => {
        setToken(null);
        setUser(null);
        Cookies.remove("token");
    };

    return (
        <AuthContext.Provider value={{user, token, login, logout}}>
            {children}
        </AuthContext.Provider>
    );

};

export const useAuth = () => useContext(AuthContext);
