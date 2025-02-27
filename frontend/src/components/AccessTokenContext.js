import React, { createContext, useContext, useState, useEffect } from 'react';

const AccessTokenContext = createContext();

export const AccessTokenProvider = ({ children }) => {
    const [accessToken, setAccessToken] = useState(() => {
        return localStorage.getItem("accessToken") || "";
    });

    return (
        <AccessTokenContext.Provider value={{ accessToken, setAccessToken }}>
            {children}
        </AccessTokenContext.Provider>
    );
};

export function useAccessToken() {
    return useContext(AccessTokenContext);
}