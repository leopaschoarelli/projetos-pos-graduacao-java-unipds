"use client";

import { use } from "react";
import { PostType } from "@/src/app/types";

type PostsProps = { posts: Promise<PostType[]> };

export default function Posts({ posts }: PostsProps) {
    const allPosts = use(posts);

    return (
        <ul>
            {allPosts.map((post) => (
                <li key={post.id}>{post.title}</li>
            ))}
        </ul>
    );
}
