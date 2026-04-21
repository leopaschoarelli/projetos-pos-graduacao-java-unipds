"use server";    

import { fetchWithToken } from "@/lib/fetchWithToken";
import { revalidateTag } from "next/cache";
import { cookies } from "next/headers";

export const handleCreateTask = async (_: string, formData: FormData) => {
    const task    = formData.get("task")?.toString();  

    if (!task) {
        return "Você precisa informar o título da Task!";
    }    

    try {
        const body = {
            title: task,
        };

        const cookieStore = await cookies();
        const token = cookieStore.get("token")?.value;

        if (!token) {
            return "Token não encontrado";
        } else {
            const { message } = await fetchWithToken(`${process.env.BACKEND_URL}/tasks`, 
            token, 
                {
                    method: 'POST',
                    body: JSON.stringify(body),
                }
            );

            if (message) {
              return message;
            } 

            revalidateTag("get-tasks");
        }
    } catch {
        console.error("handleCreateTask failed");
        return "Erro ao criar Task";
    }
}

export const handleCompleteTask = async (formData: FormData) => {

    const id = formData.get("id")?.toString();  

    if (!id) {
        console.error("Você precisa informar o ID da Task!");
        return;
    }    

    try {
        const cookieStore = await cookies();

        const token = cookieStore.get("token")?.value;

        if (!token) {
            console.error("Token não encontrado");
            return;
        } else {
            const { message } = await fetchWithToken(`${process.env.BACKEND_URL}/tasks/${id}/complete`, 
                token, 
                {
                    method: 'PUT',
                }
            );

            if (message) {
                console.error(message);
                return;
            } 

            revalidateTag("get-tasks");
        }
    } catch {
        console.error("handleCompleteTask failed");
        return;
    }
}