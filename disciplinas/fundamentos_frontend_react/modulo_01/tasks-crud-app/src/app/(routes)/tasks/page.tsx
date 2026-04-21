import { FormTasks } from "@/components/forms/FormTasks";
import { fetchWithToken } from "@/lib/fetchWithToken";
import { Metadata } from "next";
import { cookies } from "next/headers";

const PAGE_TITLE = "Tasks";

export const metadata: Metadata = {
  title: PAGE_TITLE,
};


export default function Tasks() {

  const handleCreateTask = async (_: string, formData: FormData) => {
        "use server";
        
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

            fetchWithToken(`${process.env.BACKEND_URL}/tasks`, token);
          
            const { message } = await fetchWithToken(`${process.env.BACKEND_URL}/tasks`, 
              token, 
              {
                method: 'POST',
                body: JSON.stringify(body),
              }
            );

            return message;
          }
      } catch {
        console.error("handleCreateTask failed");
        return "Erro ao criar Task";
      }
  };

  return (
    <>
      <h1 className="text-4xl text-center font-bold">{PAGE_TITLE}</h1>

      <FormTasks action={handleCreateTask} />

      <ul>
        <li>tasks...</li>
      </ul>
    </>
  );
}
