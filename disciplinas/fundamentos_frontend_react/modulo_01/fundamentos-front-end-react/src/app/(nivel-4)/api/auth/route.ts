import { NextResponse } from "next/server";
import jwt from 'jsonwebtoken';

import { User } from "@/src/app/(nivel-4)/AuthContext"

export async function POST(req: Request) {
    const { email, password } = await req.json()

    if (email === "leonardo.lhpr@gmail.com" && password === "123456") {
        const user: User = { email, role: "admin" };
        const token = jwt.sign(user, process.env.JWT_SECRET!, { expiresIn: '1h' });

        const response = NextResponse.json({ user });

        response.cookies.set('token', token, {
            httpOnly: false,
            secure: false, // true somente com https
            path: '/',
            maxAge: 60 * 60, // 1 hora
        });

        return response;
    }

    return NextResponse.json({ message: 'Credenciais inválidas' }, { status: 401 })
}
