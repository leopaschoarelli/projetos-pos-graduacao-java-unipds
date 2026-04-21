import { PostType } from "../../types";

export default async function Page() {
  const data = await fetch('https://api.vercel.app/blog');

  const posts: PostType[] = await data.json();

  return (
    <ul>
      {posts.map((post) => (
        <li key={post.id}>{post.title}</li>
      ))}
    </ul>
  );

}
