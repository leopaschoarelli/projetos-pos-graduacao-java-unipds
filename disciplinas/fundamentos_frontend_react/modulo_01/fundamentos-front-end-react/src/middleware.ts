import { NextRequest, NextResponse } from "next/server";

import jwt from "jsonwebtoken";

export function middleware(req: NextRequest) {
    const token = req.headers.get("authorization")?.split(" ")[1];
    
    if (!token) return NextResponse.redirect(new URL("/login", req.url));

    try {

        const decoded = jwt.verify(token, process.env.JWT_SECRET!);
        const role = (decoded as any).role;

        if (req.nextUrl.pathname.startsWith("/dashboard") && role !== "admin") {
            return NextResponse.redirect(new URL("/login", req.url));
        }

        return NextResponse.next();
    } catch {
        return NextResponse.redirect(new URL("/login", req.url));
    }
}

export const config = {
    matcher: ["/dashboatrd"],
};
