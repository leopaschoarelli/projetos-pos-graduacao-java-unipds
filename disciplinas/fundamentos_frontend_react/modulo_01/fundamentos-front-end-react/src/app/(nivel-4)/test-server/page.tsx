import { fetchWithToken } from "@/src/lib/nivel-4/fetchWithToken";
import { cookies } from "next/headers";


export default async function Page() {
  const cookieStore = await cookies();

  const token = cookieStore.get("token")?.value;

  if (!token) {
    return <div>Nenhum token encontrado</div>;
  }

  const response = await fetchWithToken(
    "http://localhost:3000/api/protected",
    token
  );

  const data = await response.json();

  return <div>{JSON.stringify(data)}</div>;
}