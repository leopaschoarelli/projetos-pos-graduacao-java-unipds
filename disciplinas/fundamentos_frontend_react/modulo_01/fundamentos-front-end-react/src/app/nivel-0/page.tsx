// export default function Page() {
//   return <div>Page</div>
// }

import { Hobbies } from "@/src/components/Hobbies";
import { MeuNome } from "@/src/components/MeuNome";

const Page = () => (
  <div>
    <MeuNome name="Leonardo" age={31} birthDate={new Date(1995, 2, 10)}/>
    <Hobbies />
  </div>
);

export default Page;
