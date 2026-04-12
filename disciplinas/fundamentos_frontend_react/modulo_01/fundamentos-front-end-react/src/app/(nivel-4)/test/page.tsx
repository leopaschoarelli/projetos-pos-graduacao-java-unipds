import { cookies } from "next/headers";

import { fetchWithToken } from "@/src/lib/nivel-4/fetchWithToken";

export default async function Page() {

  const cookieStore = await cookies();

  const token = cookieStore.get('token')?.value;

  if (!token) return null;

  const response = await fetchWithToken('http://localhost:3000/api/protected', token);

  const data = await response.json();

  return <div>{JSON.stringify(data)}</div>;

}
